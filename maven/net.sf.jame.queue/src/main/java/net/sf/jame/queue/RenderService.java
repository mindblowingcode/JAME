/*
 * JAME 6.1 
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2010 Andrea Medeghini
 * http://andreamedeghini.users.sourceforge.net
 *
 * This file is part of JAME.
 *
 * JAME is an application for creating fractals and other graphics artifacts.
 *
 * JAME is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JAME is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JAME.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package net.sf.jame.queue;

import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.extension.ExtensionNotFoundException;
import net.sf.jame.core.extension.ExtensionReference;
import net.sf.jame.core.util.DefaultThreadFactory;
import net.sf.jame.core.util.Tile;
import net.sf.jame.core.util.IntegerVector2D;
import net.sf.jame.core.util.Surface;
import net.sf.jame.core.util.Worker;
import net.sf.jame.queue.clip.ClipPreview;
import net.sf.jame.queue.clip.RenderClipDataRow;
import net.sf.jame.queue.job.RenderJobDataRow;
import net.sf.jame.queue.profile.RenderProfileDataRow;
import net.sf.jame.queue.spool.DefaultJobService;
import net.sf.jame.queue.spool.JobData;
import net.sf.jame.queue.spool.JobListener;
import net.sf.jame.queue.spool.JobService;
import net.sf.jame.queue.spool.JobServiceListener;
import net.sf.jame.queue.spool.SpoolJobInterface;
import net.sf.jame.queue.spool.job.CopyProcessSpoolJob;
import net.sf.jame.queue.spool.job.CopyProcessSpoolJobFactory;
import net.sf.jame.queue.spool.job.PostProcessSpoolJob;
import net.sf.jame.queue.spool.job.PostProcessSpoolJobFactory;
import net.sf.jame.twister.TwisterClip;
import net.sf.jame.twister.TwisterRuntime;
import net.sf.jame.twister.renderer.DefaultTwisterRenderer;

/**
 * @author Andrea Medeghini
 */
public class RenderService {
	public static final int SERVICE_COPY_PROCESS = 0;
	public static final int SERVICE_POST_PROCESS = 1;
	public static final int SERVICE_PROCESS = 2;
	private final Worker spoolWorker = new Worker(new DefaultThreadFactory("Spool Worker", true, Thread.NORM_PRIORITY));
	private final Worker serviceWorker = new Worker(new DefaultThreadFactory("Service Worker", true, Thread.NORM_PRIORITY));
	private final Worker previewWorker = new Worker(new DefaultThreadFactory("Preview Worker", true, Thread.NORM_PRIORITY));
	private final HashMap<Integer, String> postProcessJobs = new HashMap<Integer, String>();
	private final HashMap<Integer, String> copyProcessJobs = new HashMap<Integer, String>();
	private final HashMap<Integer, String> processJobs = new HashMap<Integer, String>();
	private final HashMap<Integer, ClipPreview> previews = new HashMap<Integer, ClipPreview>();
	private final List<JobServiceListener> listeners = new ArrayList<JobServiceListener>();
	private JobService<? extends SpoolJobInterface> processService;
	private final JobService<PostProcessSpoolJob> postProcessService;
	private final JobService<CopyProcessSpoolJob> copyProcessService;
	private final DefaultJobListener jobListener = new DefaultJobListener();
	private final DefaultJobServiceListener postProcessServicelistener = new DefaultJobServiceListener(SERVICE_COPY_PROCESS);
	private final DefaultJobServiceListener copyProcessServicelistener = new DefaultJobServiceListener(SERVICE_POST_PROCESS);
	private final DefaultJobServiceListener processServicelistener = new DefaultJobServiceListener(SERVICE_PROCESS);
	private final List<PreviewListener> previewListeners = new LinkedList<PreviewListener>();
	private ExtensionReference serviceReference;
	private final LibraryService service;

	/**
	 * @param service
	 * @param jobService
	 * @throws ExtensionException
	 * @throws ExtensionNotFoundException
	 */
	public RenderService(final LibraryService service, final ExtensionReference serviceReference) throws ExtensionException {
		this.service = service;
		this.serviceReference = serviceReference;
		service.addServiceListener(new DefaultLibraryServiceListener());
		postProcessService = new DefaultJobService<PostProcessSpoolJob>(SERVICE_POST_PROCESS, "PostProcessor", new PostProcessSpoolJobFactory(service, spoolWorker), spoolWorker);
		copyProcessService = new DefaultJobService<CopyProcessSpoolJob>(SERVICE_COPY_PROCESS, "CopyProcessor", new CopyProcessSpoolJobFactory(service, spoolWorker), spoolWorker);
		processService = RenderServiceRegistry.getInstance().getSpoolRegistry().getExtension(serviceReference.getExtensionId()).createExtensionRuntime().getJobService(SERVICE_PROCESS, service, spoolWorker);
		postProcessService.addServiceListener(postProcessServicelistener);
		copyProcessService.addServiceListener(copyProcessServicelistener);
		processService.addServiceListener(processServicelistener);
		postProcessService.start();
		copyProcessService.start();
		processService.start();
		previewWorker.start();
		serviceWorker.start();
		spoolWorker.start();
	}

	/**
	 * @param jobService
	 */
	public synchronized void setJobServiceReference(final ExtensionReference serviceReference) {
		this.serviceReference = serviceReference;
		serviceWorker.addTask(new ServiceTask<Object>(new ServiceCallback<Object>() {
				public LibraryService execute(final LibraryService service) throws Exception {
				if (processService != null) {
					processService.stop();
					processService.removeServiceListener(processServicelistener);
				}
				processJobs.clear();
				processService = RenderServiceRegistry.getInstance().getSpoolRegistry().getExtension(serviceReference.getExtensionId()).createExtensionRuntime().getJobService(SERVICE_PROCESS, service, spoolWorker);
				processService.addServiceListener(processServicelistener);
				processService.start();
				return null;
				}

			public void executed(final Object value) {
				}

			public void failed(final Throwable throwable) {
				}
						}));
	}

	/**
	 * @return the serviceReference
	 */
	public synchronized ExtensionReference getJobServiceReference() {
		return serviceReference;
	}

	/**
	 * 
	 */
	public void start() {
		spoolWorker.start();
		serviceWorker.start();
		previewWorker.start();
		postProcessService.start();
		copyProcessService.start();
		if (processService != null) {
			processService.start();
		}
	}

	/**
	 * 
	 */
	public void stop() {
		spoolWorker.stop();
		serviceWorker.stop();
		previewWorker.stop();
		copyProcessService.stop();
		postProcessService.stop();
		if (processService != null) {
			processService.stop();
		}
	}

	/**
	 * @return
	 */
	public LibraryService getLibraryService() {
		return service;
	}

	/**
	 * @param task
	 */
	public <E> void execute(final ServiceCallback<E> callback) {
		serviceWorker.addTask(new ServiceTask<E>(callback));
	}

	/**
	 * @param listener
	 */
	public void addServiceListener(final LibraryServiceListener listener) {
		service.addServiceListener(listener);
	}

	/**
	 * @param listener
	 */
	public void removeServiceListener(final LibraryServiceListener listener) {
		service.removeServiceListener(listener);
	}

	/**
	 * @param listener
	 */
	public void addJobServiceListener(final JobServiceListener listener) {
		synchronized (listeners) {
			listeners.add(listener);
		}
	}

	/**
	 * @param listener
	 */
	public void removeJobServiceListener(final JobServiceListener listener) {
		synchronized (listeners) {
			listeners.remove(listener);
		}
	}

	/**
	 * @param listener
	 */
	public void addPreviewListener(final PreviewListener listener) {
		synchronized (previewListeners) {
			previewListeners.add(listener);
		}
	}

	/**
	 * @param listener
	 */
	public void removePreviewListener(final PreviewListener listener) {
		synchronized (previewListeners) {
			previewListeners.remove(listener);
		}
	}

	/**
	 * 
	 */
	protected void firePreviewUpdated(final int clipId) {
		synchronized (previewListeners) {
			for (PreviewListener listener : previewListeners) {
				listener.updated(clipId);
			}
		}
	}

	/**
	 * @return
	 */
	public ClipPreview getClipPreview(final int clipId) {
		return previews.get(clipId);
	}

	private class ServiceTask<T> implements Runnable {
		private final ServiceCallback<T> callback;

		/**
		 * @param callback
		 * @param hasValue
		 */
		public ServiceTask(final ServiceCallback<T> callback) {
			this.callback = callback;
		}

		public final void run() {
			try {
				callback.executed(callback.execute(service));
			}
			catch (final Exception e) {
				e.printStackTrace();
				callback.failed(e);
			}
		}
	}

	public interface ServiceCallback<T> {
		/**
		 * @param service
		 * @return
		 * @throws Exception
		 */
		public T execute(LibraryService service) throws Exception;

		/**
		 * @param throwable
		 */
		public void failed(Throwable throwable);

		/**
		 * @param value
		 */
		public void executed(T value);
	}

	private class DefaultLibraryServiceListener implements LibraryServiceListener {
		/**
		 * @see net.sf.jame.queue.LibraryServiceListener#clipCreated(net.sf.jame.queue.clip.RenderClipDataRow)
		 */
		public void clipCreated(final RenderClipDataRow clip) {
			ClipPreview preview = previews.get(clip.getClipId());
			if (preview == null) {
				preview = new DefaultClipPreview(clip.getClipId());
				previews.put(clip.getClipId(), preview);
			}
			preview.update();
		}

		/**
		 * @see net.sf.jame.queue.LibraryServiceListener#clipDeleted(net.sf.jame.queue.clip.RenderClipDataRow)
		 */
		public void clipDeleted(final RenderClipDataRow clip) {
			ClipPreview preview = previews.remove(clip.getClipId());
			if (preview != null) {
				preview.dispose();
			}
		}

		/**
		 * @see net.sf.jame.queue.LibraryServiceListener#clipUpdated(net.sf.jame.queue.clip.RenderClipDataRow)
		 */
		public void clipUpdated(final RenderClipDataRow clip) {
		}

		/**
		 * @see net.sf.jame.queue.LibraryServiceListener#clipUpdated(net.sf.jame.queue.clip.RenderClipDataRow)
		 */
		public void clipLoaded(final RenderClipDataRow clip) {
			ClipPreview preview = previews.get(clip.getClipId());
			if (preview == null) {
				preview = new DefaultClipPreview(clip.getClipId());
				previews.put(clip.getClipId(), preview);
			}
			preview.update();
		}

		/**
		 * @see net.sf.jame.queue.LibraryServiceListener#jobCreated(net.sf.jame.queue.job.RenderJobDataRow)
		 */
		public void jobCreated(final RenderJobDataRow job) {
		}

		/**
		 * @see net.sf.jame.queue.LibraryServiceListener#jobDeleted(net.sf.jame.queue.job.RenderJobDataRow)
		 */
		public void jobDeleted(final RenderJobDataRow job) {
			if (job.isPostProcess()) {
				final String jobId = postProcessJobs.remove(job.getJobId());
				if (jobId != null) {
					postProcessService.deleteJob(jobId);
				}
			}
			else if (job.isCopyProcess()) {
				final String jobId = copyProcessJobs.remove(job.getJobId());
				if (jobId != null) {
					copyProcessService.deleteJob(jobId);
				}
			}
			else if (job.isProcess()) {
				final String jobId = processJobs.remove(job.getJobId());
				if (jobId != null) {
					processService.deleteJob(jobId);
				}
			}
		}

		/**
		 * @see net.sf.jame.queue.LibraryServiceListener#jobStarted(net.sf.jame.queue.job.RenderJobDataRow)
		 */
		public void jobStarted(final RenderJobDataRow job) {
			if (job.isPostProcess()) {
				if (!postProcessJobs.containsKey(job.getJobId())) {
					final String jobId = postProcessService.createJob(jobListener);
					postProcessService.setJobData(jobId, job, job.getFrameNumber());
					postProcessJobs.put(job.getJobId(), jobId);
					if (jobId != null) {
						postProcessService.runJob(jobId);
					}
				}
				else {
					final String jobId = postProcessJobs.get(job.getJobId());
					if (jobId != null) {
						postProcessService.runJob(jobId);
					}
				}
			}
			else if (job.isCopyProcess()) {
				if (!copyProcessJobs.containsKey(job.getJobId())) {
					final String jobId = copyProcessService.createJob(jobListener);
					copyProcessService.setJobData(jobId, job, job.getFrameNumber());
					copyProcessJobs.put(job.getJobId(), jobId);
					if (jobId != null) {
						copyProcessService.runJob(jobId);
					}
				}
				else {
					final String jobId = copyProcessJobs.get(job.getJobId());
					if (jobId != null) {
						copyProcessService.runJob(jobId);
					}
				}
			}
			else if (job.isProcess()) {
				if (!processJobs.containsKey(job.getJobId())) {
					final String jobId = processService.createJob(jobListener);
					processService.setJobData(jobId, job, job.getFrameNumber());
					processJobs.put(job.getJobId(), jobId);
					if (jobId != null) {
						processService.runJob(jobId);
					}
				}
				else {
					final String jobId = processJobs.get(job.getJobId());
					if (jobId != null) {
						processService.runJob(jobId);
					}
				}
			}
		}

		/**
		 * @see net.sf.jame.queue.LibraryServiceListener#jobAborted(net.sf.jame.queue.job.RenderJobDataRow)
		 */
		public void jobAborted(final RenderJobDataRow job) {
			if (job.isPostProcess()) {
				final String jobId = postProcessJobs.get(job.getJobId());
				if (jobId != null) {
					postProcessService.abortJob(jobId);
				}
			}
			else if (job.isCopyProcess()) {
				final String jobId = copyProcessJobs.get(job.getJobId());
				if (jobId != null) {
					copyProcessService.abortJob(jobId);
				}
			}
			else if (job.isProcess()) {
				final String jobId = processJobs.get(job.getJobId());
				if (jobId != null) {
					processService.abortJob(jobId);
				}
			}
		}

		/**
		 * @see net.sf.jame.queue.LibraryServiceListener#jobStopped(net.sf.jame.queue.job.RenderJobDataRow)
		 */
		public void jobStopped(final RenderJobDataRow job) {
			if (job.isPostProcess()) {
				final String jobId = postProcessJobs.get(job.getJobId());
				if (jobId != null) {
					postProcessService.stopJob(jobId);
				}
			}
			else if (job.isCopyProcess()) {
				final String jobId = copyProcessJobs.get(job.getJobId());
				if (jobId != null) {
					copyProcessService.stopJob(jobId);
				}
			}
			else if (job.isProcess()) {
				final String jobId = processJobs.get(job.getJobId());
				if (jobId != null) {
					processService.stopJob(jobId);
				}
			}
		}

		/**
		 * @see net.sf.jame.queue.LibraryServiceListener#jobUpdated(net.sf.jame.queue.job.RenderJobDataRow)
		 */
		public void jobUpdated(final RenderJobDataRow job) {
		}

		/**
		 * @see net.sf.jame.queue.LibraryServiceListener#jobResumed(net.sf.jame.queue.job.RenderJobDataRow)
		 */
		public void jobResumed(final RenderJobDataRow job) {
			if (job.isPostProcess()) {
				if (!postProcessJobs.containsKey(job.getJobId())) {
					final String jobId = postProcessService.createJob(jobListener);
					postProcessService.setJobData(jobId, job, job.getFrameNumber());
					postProcessJobs.put(job.getJobId(), jobId);
				}
			}
			else if (job.isCopyProcess()) {
				if (!copyProcessJobs.containsKey(job.getJobId())) {
					final String jobId = copyProcessService.createJob(jobListener);
					copyProcessService.setJobData(jobId, job, job.getFrameNumber());
					copyProcessJobs.put(job.getJobId(), jobId);
				}
			}
			else if (job.isProcess()) {
				if (!processJobs.containsKey(job.getJobId())) {
					final String jobId = processService.createJob(jobListener);
					processService.setJobData(jobId, job, job.getFrameNumber());
					processJobs.put(job.getJobId(), jobId);
				}
			}
		}

		/**
		 * @see net.sf.jame.queue.LibraryServiceListener#profileCreated(net.sf.jame.queue.profile.RenderProfileDataRow)
		 */
		public void profileCreated(final RenderProfileDataRow profile) {
		}

		/**
		 * @see net.sf.jame.queue.LibraryServiceListener#profileDeleted(net.sf.jame.queue.profile.RenderProfileDataRow)
		 */
		public void profileDeleted(final RenderProfileDataRow profile) {
		}

		/**
		 * @see net.sf.jame.queue.LibraryServiceListener#profileUpdated(net.sf.jame.queue.profile.RenderProfileDataRow)
		 */
		public void profileUpdated(final RenderProfileDataRow profile) {
		}

		/**
		 * @see net.sf.jame.queue.LibraryServiceListener#profileLoaded(net.sf.jame.queue.profile.RenderProfileDataRow)
		 */
		public void profileLoaded(final RenderProfileDataRow profile) {
		}
	}

	private class DefaultJobListener implements JobListener {
		/**
		 * @see net.sf.jame.queue.spool.JobListener#updated(String, JobData)
		 */
		public void updated(final String jobId, final JobData jobData) {
			execute(new ServiceCallback<Object>() {
				public Object execute(final LibraryService service) throws Exception {
					try {
						service.processUpdated(jobData);
					}
					catch (Exception e) {
					}
					return null;
				}

				public void executed(final Object value) {
				}

				public void failed(final Throwable throwable) {
				}
			});
		}

		/**
		 * @see net.sf.jame.queue.spool.JobListener#started(String, JobData)
		 */
		public void started(final String jobId, final JobData jobData) {
			execute(new ServiceCallback<Object>() {
				public Object execute(final LibraryService service) throws Exception {
					try {
						service.processStarted(jobData);
					}
					catch (Exception e) {
					}
					return null;
				}

				public void executed(final Object value) {
				}

				public void failed(final Throwable throwable) {
				}
			});
		}

		/**
		 * @see net.sf.jame.queue.spool.JobListener#stopped(String, JobData)
		 */
		public void stopped(final String jobId, final JobData jobData) {
			execute(new ServiceCallback<Object>() {
				public Object execute(final LibraryService service) throws Exception {
					try {
						service.processStopped(jobData);
					}
					catch (Exception e) {
					}
					return null;
				}

				public void executed(final Object value) {
				}

				public void failed(final Throwable throwable) {
				}
			});
		}

		/**
		 * @see net.sf.jame.queue.spool.JobListener#terminated(String, JobData)
		 */
		public void terminated(final String jobId, final JobData jobData) {
			execute(new ServiceCallback<Object>() {
				public Object execute(final LibraryService service) throws Exception {
					try {
						service.processTerminated(jobData);
					}
					catch (Exception e) {
					}
					return null;
				}

				public void executed(final Object value) {
				}

				public void failed(final Throwable throwable) {
				}
			});
		}

		/**
		 * @see net.sf.jame.queue.spool.JobListener#terminated(String, JobData)
		 */
		public void disposed(final String jobId, final JobData jobData) {
			execute(new ServiceCallback<Object>() {
				public Object execute(final LibraryService service) throws Exception {
					try {
						service.processDeleted(jobData);
					}
					catch (Exception e) {
					}
					return null;
				}

				public void executed(final Object value) {
				}

				public void failed(final Throwable throwable) {
				}
			});
		}
	}

	private class DefaultJobServiceListener implements JobServiceListener {
		private final int serviceId;

		/**
		 * @param serviceId
		 */
		public DefaultJobServiceListener(final int serviceId) {
			this.serviceId = serviceId;
		}

		public void stateChanged(final int serviceId, final int status, final String message) {
			synchronized (listeners) {
				for (JobServiceListener listener : listeners) {
					listener.stateChanged(DefaultJobServiceListener.this.serviceId, status, message);
				}
			}
		}
	}

	private class DefaultClipPreview implements ClipPreview {
		private final int clipId;
		private Surface surface;

		/**
		 * @param clipId
		 */
		public DefaultClipPreview(final int clipId) {
			this.clipId = clipId;
			surface = new Surface(20, 20);
		}

		private void render(final int clipId) {
			previewWorker.addTask(new Runnable() {
				public void run() {
					try {
						RenderClipDataRow dataRow = service.getClip(clipId);
						final TwisterClip clip = service.loadClip(dataRow);
						if (clip.getSequenceCount() > 0) {
							TwisterRuntime runtime = new TwisterRuntime(clip.getSequence(0).getFinalConfig());
							DefaultTwisterRenderer renderer = new DefaultTwisterRenderer(runtime);
							final IntegerVector2D size = new IntegerVector2D(surface.getWidth(), surface.getHeight());
							renderer.setTile(new Tile(size, size, new IntegerVector2D(0, 0), new IntegerVector2D(0, 0)));
							renderer.prepareImage(false);
							renderer.startRenderer();
							renderer.joinRenderer();
							renderer.drawSurface(surface.getGraphics2D());
							renderer.dispose();
							firePreviewUpdated(clipId);
						}
					}
					catch (Exception e) {
					}
				}
			});
		}

		/**
		 * @see net.sf.jame.queue.clip.ClipPreview#getImage()
		 */
		public Image getImage() {
			return surface.getImage();
		}

		/**
		 * @see net.sf.jame.queue.clip.ClipPreview#dispose()
		 */
		public void dispose() {
			if (surface != null) {
				surface.dispose();
				surface = null;
			}
		}

		/**
		 * @see net.sf.jame.queue.clip.ClipPreview#update()
		 */
		public void update() {
			render(clipId);
		}
	}
}
