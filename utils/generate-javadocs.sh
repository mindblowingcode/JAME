export ROOT=`pwd`/..

classespath=''
classespath=$classespath:$ROOT/maven/net.sf.jame.core/target/classes
classespath=$classespath:$ROOT/maven/net.sf.jame.core.swing/target/classes
classespath=$classespath:$ROOT/maven/net.sf.jame.twister/target/classes
classespath=$classespath:$ROOT/maven/net.sf.jame.twister.swing/target/classes
classespath=$classespath:$ROOT/maven/net.sf.jame.mandelbrot/target/classes
classespath=$classespath:$ROOT/maven/net.sf.jame.mandelbrot.swing/target/classes
#classespath=$classespath:$ROOT/maven/net.sf.jame.contextfree/target/classes
#classespath=$classespath:$ROOT/maven/net.sf.jame.contextfree.swing/target/classes
classespath=$classespath:$ROOT/maven/net.sf.jame.twister/target/classes
classespath=$classespath:$ROOT/maven/net.sf.jame.twister.swing/target/classes
classespath=$classespath:$ROOT/maven/net.sf.jame.queue/target/classes
classespath=$classespath:$ROOT/maven/net.sf.jame.maven/target/classes
classespath=$classespath:$ROOT/maven/net.sf.jame.devtools/target/classes

sourcespath=''
sourcespath=$sourcespath:$ROOT/maven/net.sf.jame.core/src
sourcespath=$sourcespath:$ROOT/maven/net.sf.jame.core.swing/src
sourcespath=$sourcespath:$ROOT/maven/net.sf.jame.twister/src
sourcespath=$sourcespath:$ROOT/maven/net.sf.jame.twister.swing/src
sourcespath=$sourcespath:$ROOT/maven/net.sf.jame.mandelbrot/src
sourcespath=$sourcespath:$ROOT/maven/net.sf.jame.mandelbrot.swing/src
sourcespath=$sourcespath:$ROOT/maven/net.sf.jame.queue/src
sourcespath=$sourcespath:$ROOT/maven/net.sf.jame.main/src
sourcespath=$sourcespath:$ROOT/maven/net.sf.jame.devtools/src
#sourcespath=$sourcespath:$ROOT/maven/net.sf.jame.contextfree/src
#sourcespath=$sourcespath:$ROOT/maven/net.sf.jame.contextfree.swing/src

opts='-J-Xmx512M -author -version -use -d javadocs -subpackages net.sf.jame'

javadoc -classpath $classespath -sourcepath $sourcespath $opts 2>generate-javadocs.log
