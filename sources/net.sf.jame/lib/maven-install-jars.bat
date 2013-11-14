del *x86.jar
del *x86_64.jar

zip -j net.sf.freeimage4java.native-0.1.1-linux_x86.jar net.sf.freeimage4java/linux/x86/libfreeimage4java.so 
zip -j net.sf.freeimage4java.native-0.1.1-linux_x86_64.jar net.sf.freeimage4java/linux/x86_64/libfreeimage4java.so 

zip -j net.sf.freeimage4java.native-0.1.1-win32_x86.jar net.sf.freeimage4java/win32/x86/freeimage4java.dll 
zip -j net.sf.freeimage4java.native-0.1.1-win32_x86_64.jar net.sf.freeimage4java/win32/x86_64/freeimage4java.dll 

zip -j net.sf.freeimage4java.native-0.1.1-osx_x86.jar net.sf.freeimage4java/osx/x86/libfreeimage4java.jnilib 
zip -j net.sf.freeimage4java.native-0.1.1-osx_x86_64.jar net.sf.freeimage4java/osx/x86_64/libfreeimage4java.jnilib 

zip -j net.sf.ffmpeg4java.native-0.1.2-linux_x86.jar net.sf.ffmpeg4java/linux/x86/libffmpeg4java.so 
zip -j net.sf.ffmpeg4java.native-0.1.2-linux_x86_64.jar net.sf.ffmpeg4java/linux/x86_64/libffmpeg4java.so 

zip -j net.sf.ffmpeg4java.native-0.1.2-win32_x86.jar net.sf.ffmpeg4java/win32/x86/ffmpeg4java.dll 
zip -j net.sf.ffmpeg4java.native-0.1.2-win32_x86_64.jar net.sf.ffmpeg4java/win32/x86_64/ffmpeg4java.dll

zip -j net.sf.ffmpeg4java.native-0.1.2-osx_x86.jar net.sf.ffmpeg4java/osx/x86/libffmpeg4java.jnilib 
zip -j net.sf.ffmpeg4java.native-0.1.2-osx_x86_64.jar net.sf.ffmpeg4java/osx/x86_64/libffmpeg4java.jnilib 

mvn install:install-file -Dpackaging=jar -DgroupId=net.sf.freeimage4java -Dversion=0.1.1 \
	-Dclassifier=linux_x86 -DartifactId=net.sf.freeimage4java.native -Dfile=net.sf.freeimage4java.native-0.1.1-linux_x86.jar
mvn install:install-file -Dpackaging=jar -DgroupId=net.sf.freeimage4java -Dversion=0.1.1 \
	-Dclassifier=linux_x86_64 -DartifactId=net.sf.freeimage4java.native -Dfile=net.sf.freeimage4java.native-0.1.1-linux_x86_64.jar

mvn install:install-file -Dpackaging=jar -DgroupId=net.sf.freeimage4java -Dversion=0.1.1 \
	-Dclassifier=win32_x86 -DartifactId=net.sf.freeimage4java.native -Dfile=net.sf.freeimage4java.native-0.1.1-win32_x86.jar
mvn install:install-file -Dpackaging=jar -DgroupId=net.sf.freeimage4java -Dversion=0.1.1 \
	-Dclassifier=win32_x86_64 -DartifactId=net.sf.freeimage4java.native -Dfile=net.sf.freeimage4java.native-0.1.1-win32_x86_64.jar

mvn install:install-file -Dpackaging=jar -DgroupId=net.sf.freeimage4java -Dversion=0.1.1 \
	-Dclassifier=osx_x86 -DartifactId=net.sf.freeimage4java.native -Dfile=net.sf.freeimage4java.native-0.1.1-osx_x86.jar
mvn install:install-file -Dpackaging=jar -DgroupId=net.sf.freeimage4java -Dversion=0.1.1 \
	-Dclassifier=osx_x86_64 -DartifactId=net.sf.freeimage4java.native -Dfile=net.sf.freeimage4java.native-0.1.1-osx_x86_64.jar

mvn install:install-file -Dpackaging=jar -DgroupId=net.sf.ffmpeg4java -Dversion=0.1.2 \
	-Dclassifier=linux_x86 -DartifactId=net.sf.ffmpeg4java.native -Dfile=net.sf.ffmpeg4java.native-0.1.2-linux_x86.jar
mvn install:install-file -Dpackaging=jar -DgroupId=net.sf.ffmpeg4java -Dversion=0.1.2 \
	-Dclassifier=linux_x86_64 -DartifactId=net.sf.ffmpeg4java.native -Dfile=net.sf.ffmpeg4java.native-0.1.2-linux_x86_64.jar

mvn install:install-file -Dpackaging=jar -DgroupId=net.sf.ffmpeg4java -Dversion=0.1.2 \
	-Dclassifier=win32_x86 -DartifactId=net.sf.ffmpeg4java.native -Dfile=net.sf.ffmpeg4java.native-0.1.2-win32_x86.jar
mvn install:install-file -Dpackaging=jar -DgroupId=net.sf.ffmpeg4java -Dversion=0.1.2 \
	-Dclassifier=win32_x86_64 -DartifactId=net.sf.ffmpeg4java.native -Dfile=net.sf.ffmpeg4java.native-0.1.2-win32_x86_64.jar

mvn install:install-file -Dpackaging=jar -DgroupId=net.sf.ffmpeg4java -Dversion=0.1.2 \
	-Dclassifier=osx_x86 -DartifactId=net.sf.ffmpeg4java.native -Dfile=net.sf.ffmpeg4java.native-0.1.2-osx_x86.jar
mvn install:install-file -Dpackaging=jar -DgroupId=net.sf.ffmpeg4java -Dversion=0.1.2 \
	-Dclassifier=osx_x86_64 -DartifactId=net.sf.ffmpeg4java.native -Dfile=net.sf.ffmpeg4java.native-0.1.2-osx_x86_64.jar

mvn install:install-file -Dpackaging=jar -DgroupId=net.sf.freeimage4java -Dversion=0.1.1 \
	-DartifactId=net.sf.freeimage4java -Dfile=net.sf.freeimage4java/freeimage4java.jar

mvn install:install-file -Dpackaging=jar -DgroupId=net.sf.ffmpeg4java -Dversion=0.1.2 \
	-DartifactId=net.sf.ffmpeg4java -Dfile=net.sf.ffmpeg4java/ffmpeg4java.jar

del *x86.jar
del *x86_64.jar
