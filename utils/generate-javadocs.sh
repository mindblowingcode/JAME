export ROOT=`pwd`/..

classespath=''
classespath=$classespath:$ROOT/maven/net.sf.jame.core/target/classes
classespath=$classespath:$ROOT/maven/net.sf.jame.twister/target/classes
classespath=$classespath:$ROOT/maven/net.sf.jame.mandelbrot/target/classes
#classespath=$classespath:$ROOT/maven/net.sf.jame.contextfree/target/classes
classespath=$classespath:$ROOT/maven/net.sf.jame.core.swing/target/classes
classespath=$classespath:$ROOT/maven/net.sf.jame.twister.swing/target/classes
classespath=$classespath:$ROOT/maven/net.sf.jame.mandelbrot.swing/target/classes
#classespath=$classespath:$ROOT/maven/net.sf.jame.contextfree.swing/target/classes
classespath=$classespath:$ROOT/maven/net.sf.jame.queue/target/classes
classespath=$classespath:$ROOT/maven/net.sf.jame.maven/target/classes
classespath=$classespath:$ROOT/maven/net.sf.jame.devtools/target/classes

sourcespath=''
sourcespath=$sourcespath:$ROOT/maven/net.sf.jame.core/src/main/java
sourcespath=$sourcespath:$ROOT/maven/net.sf.jame.twister/src/main/java
sourcespath=$sourcespath:$ROOT/maven/net.sf.jame.mandelbrot/src/main/java
#sourcespath=$sourcespath:$ROOT/maven/net.sf.jame.contextfree/src/main/java
sourcespath=$sourcespath:$ROOT/maven/net.sf.jame.core.swing/src/main/java
sourcespath=$sourcespath:$ROOT/maven/net.sf.jame.twister.swing/src/main/java
sourcespath=$sourcespath:$ROOT/maven/net.sf.jame.mandelbrot.swing/src/main/java
#sourcespath=$sourcespath:$ROOT/maven/net.sf.jame.contextfree.swing/src/main/java
sourcespath=$sourcespath:$ROOT/maven/net.sf.jame.queue/src/main/java
sourcespath=$sourcespath:$ROOT/maven/net.sf.jame.main/src/main/java
sourcespath=$sourcespath:$ROOT/maven/net.sf.jame.devtools/src/main/java

opts='-J-Xmx512M -author -version -use -d javadocs -subpackages net.sf.jame'

javadoc -classpath $classespath -sourcepath $sourcespath $opts 2>generate-javadocs.log
