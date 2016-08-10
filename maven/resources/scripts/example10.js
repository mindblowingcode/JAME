// This example shows how to do introspection in JAME JavaScript API.

// JAME JavaScript API supports inclusion by #include directive. 
#include "util.js"

// JAME JavaScript API supports supports introspection to discover  
// available enumerators, constructors, creators and extensions.
// Enumerators, constructors, creators and extensions are provided 
// by the plug-ins deployed as OSGi bundles in the platform.

var enumerator = JAMEContext.getEnumerator("type.class.Enumerator");
var enumerators = enumerator.listExtensions();
JAMEContext.println("Available enumerators:");
for (i = 0; i < enumerators.size(); i++) {
	JAMEContext.println(enumerators.get(i));
}

var enumerator = JAMEContext.getEnumerator("type.class.Constructor");
var constructors = enumerator.listExtensions();
JAMEContext.println("\n\nAvailable constructors:");
for (i = 0; i < constructors.size(); i++) {
	JAMEContext.println(constructors.get(i));
}

var enumerator = JAMEContext.getEnumerator("type.class.Creator");
var creators = enumerator.listExtensions();
JAMEContext.println("\n\nAvailable creators:");
for (i = 0; i < creators.size(); i++) {
	JAMEContext.println(creators.get(i));
}

// The special enumerator "type.class.Enumerator" can be used to discover all available extensions. 
var enumerator = JAMEContext.getEnumerator("type.class.Enumerator");
for (i = 0; i < enumerators.size(); i++) {
	// The method getExtension returns an extension which represents an enumerator.
	var extension = enumerator.getExtension(enumerators.get(i));
	// The extension can be used to get an enumerator and print the list of extensions.
	printExtensions(JAMEContext.getEnumerator(extension.getExtensionId()));
	JAMEContext.println("\n");
}
