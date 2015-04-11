// This script contains some useful functions.

function printExtensions(enumerator) {
	JAMEContext.println("Available extensions for enumerator " + extension.getExtensionId() + ":");
	var extensions = enumerator.listExtensions();
	for (var i = 0; i < extensions.size(); i++) {
		JAMEContext.println(extensions.get(i));
	}
}

function dumpTree() {
	JAMEContext.println(JAMETree.dump());
}

function show(message) {
    JAMEContext.showMessage(message, 4, 5, 92, 1000, true);
}

