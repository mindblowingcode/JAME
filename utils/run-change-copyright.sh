export ROOT=`pwd`/..

java -classpath FileUtility.jar net.sf.jame.fileutility.ChangeCopyright copyright.txt $ROOT/maven

java -classpath FileUtility.jar net.sf.jame.fileutility.ChangeCopyright copyright3.txt $ROOT/maven/net.sf.jame.mandelbrot/src/main/java/net/sf/jame/mandelbrot/renderer/BestXaosMandelbrotRenderer.java
java -classpath FileUtility.jar net.sf.jame.fileutility.ChangeCopyright copyright3.txt $ROOT/maven/net.sf.jame.mandelbrot/src/main/java/net/sf/jame/mandelbrot/renderer/FastXaosMandelbrotRenderer.java
