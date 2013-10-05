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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.jame.core.util.ConnectionFactory;

/**
 * @author Andrea Medeghini
 */
public class Session {
	private static final Logger logger = Logger.getLogger(Session.class.getName());
	private final ConnectionFactory factory;
	private Connection connection;
	private volatile Savepoint savepoint;
	private volatile int transactionId;

	/**
	 * @param factory
	 */
	public Session(final ConnectionFactory factory) {
		this.factory = factory;
	}

	/**
	 * @see java.lang.Object#finalize()
	 */
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		close();
	}

	private Connection getConnection() throws SessionException {
		if (connection == null) {
			open();
		}
		return connection;
	}

	/**
	 * @throws SessionException
	 */
	public synchronized void open() throws SessionException {
		try {
			if (Session.logger.isLoggable(Level.FINER)) {
				Session.logger.finer("Open connection");
			}
			connection = factory.createConnection();
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			connection.setAutoCommit(false);
		}
		catch (final SQLException e) {
			throw new SessionException(e);
		}
	}

	/**
	 * @throws SQLException
	 */
	public synchronized void close() throws SessionException {
		try {
			if (connection != null) {
				if (Session.logger.isLoggable(Level.FINER)) {
					Session.logger.finer("Close connection");
				}
				connection.close();
				connection = null;
				savepoint = null;
			}
		}
		catch (final SQLException e) {
			throw new SessionException(e);
		}
	}

	/**
	 * @param stmt
	 * @throws DataTableException
	 */
	public synchronized void closeStatement(final Statement stmt) throws SessionException {
		if (stmt != null) {
			try {
				stmt.close();
			}
			catch (final SQLException e) {
				throw new SessionException(e);
			}
		}
	}

	/**
	 * @param sql
	 * @return
	 * @throws SessionException
	 */
	public synchronized PreparedStatement prepareStatement(final String sql) throws SessionException {
		try {
			return getConnection().prepareStatement(sql);
		}
		catch (final SQLException e) {
			throw new SessionException(e);
		}
	}

	/**
	 * @return
	 * @throws SessionException
	 */
	public synchronized Statement createStatement() throws SessionException {
		try {
			return getConnection().createStatement();
		}
		catch (final SQLException e) {
			throw new SessionException(e);
		}
	}

	/**
	 * @throws TransactionException
	 * @throws InterruptedException
	 */
	public synchronized void openTransaction() throws TransactionException, InterruptedException {
		while (savepoint != null) {
			if (Session.logger.isLoggable(Level.FINER)) {
				Session.logger.finer("Waiting for transaction...");
			}
			wait(10000);
		}
		try {
			transactionId += 1;
			if (Session.logger.isLoggable(Level.FINER)) {
				Session.logger.finer("Open transaction: " + transactionId);
			}
			savepoint = getConnection().setSavepoint();
		}
		catch (final SQLException e) {
			throw new TransactionException("Transaction error: " + transactionId, e);
		}
		catch (final SessionException e) {
			throw new TransactionException("Transaction error: " + transactionId, e);
		}
	}

	/**
	 * @throws TransactionException
	 */
	public synchronized void abortTransaction() throws TransactionException {
		try {
			if (Session.logger.isLoggable(Level.FINER)) {
				Session.logger.finer("Abort transaction: " + transactionId);
			}
			if (savepoint == null) {
				throw new TransactionException("Transaction not opened: " + transactionId);
			}
			if (savepoint != null) {
				getConnection().rollback(savepoint);
			}
			else {
				getConnection().rollback();
			}
		}
		catch (final SQLException e) {
			throw new TransactionException("Transaction error: " + transactionId, e);
		}
		catch (final SessionException e) {
			throw new TransactionException("Transaction error: " + transactionId, e);
		}
		finally {
			savepoint = null;
		}
		notify();
	}

	/**
	 * @throws TransactionException
	 */
	public synchronized void closeTransaction() throws TransactionException {
		try {
			if (Session.logger.isLoggable(Level.FINER)) {
				Session.logger.finer("Close transaction: " + transactionId);
			}
			if (savepoint == null) {
				throw new TransactionException("Transaction not opened: " + transactionId);
			}
			getConnection().commit();
		}
		catch (final SQLException e) {
			throw new TransactionException("Transaction error: " + transactionId, e);
		}
		catch (final SessionException e) {
			throw new TransactionException("Transaction error: " + transactionId, e);
		}
		finally {
			savepoint = null;
		}
		notify();
	}
}
