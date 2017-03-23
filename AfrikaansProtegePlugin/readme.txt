Prerequisites for building:
    - Ant installed (if on Windows, this includes manually setting the ANT_HOME env variable
    and appending it to the PATH variable, as well as making sure you have a path variable pointing at your JDK)

Build instructions:
    - Set an environment variable called PROTEGE_HOME to point at the filepath of your Protege distribution.
    - To generate a jar to copy to Protege's plugins folder, run 'ant jar'
    - To install directly to Protege's plugin folder without the need to drop the jar manually,
    run 'ant install'
	
Using the Plugin:
	- To see the plugin in action, open Protege with some ontology
	- Navigate to Window -> Views -> Class Views -> Afrikaanse Klasbeskrywing
    - Select where you want the view to be on the screen
	- Click on an entity like you normally would and the view will display the associated axioms in Afrikaans