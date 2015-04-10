export ROOT=/Users/andrea/Documents/projects/JAME/project/bundles

java -classpath FileUtility.jar net.sf.jame.fileutility.LineCount $ROOT/main >report.txt
java -classpath FileUtility.jar net.sf.jame.fileutility.LineCount $ROOT/experimental >>report.txt

export ROOT=/Users/andrea/Documents/projects/JAME/project/test

java -classpath FileUtility.jar net.sf.jame.fileutility.LineCount $ROOT/main >>report.txt
java -classpath FileUtility.jar net.sf.jame.fileutility.LineCount $ROOT/experimental >>report.txt
