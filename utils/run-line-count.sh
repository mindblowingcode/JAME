export ROOT=/Users/andrea/Documents/progetti/jame
java -classpath FileUtility.jar net.sf.jame.fileutility.LineCount $ROOT/main >report.txt
java -classpath FileUtility.jar net.sf.jame.fileutility.LineCount $ROOT/experimental >>report.txt
java -classpath FileUtility.jar net.sf.jame.fileutility.LineCount $ROOT/test >>report.txt
