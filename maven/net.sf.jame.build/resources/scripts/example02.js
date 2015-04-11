// Read example01.js before to read this example.
// This example shows how to perform some delayed operations.

JAMEContext.println("I'll wait for 5 seconds...");

// The method sleep suspends the execution for a specified amount of time.
// If the user clicks on the visualization window before the specified 
// amount of time, the execution is resumed and the method returns true.
if (JAMEContext.sleep(5000)) {
    JAMEContext.println("The script has been interrupted by the user.");
} else {
    JAMEContext.println("I have finished.");
}
