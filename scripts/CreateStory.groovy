includeTargets << grailsScript("Init")

target(main: "Create a new story") {
    depends(parseArguments)
    
    if(!argsMap["params"]) {
        println "Story name not specified."
    	exit(1)
    }

    File dir = new File("${basedir}/test/stories/")
    File scriptFile = new File(dir, "${argsMap.get("params").get(0)}Story.groovy")
    scriptFile << "story \"a new story\", {\n    scenario \"a new scenario\", {\n        //TODO: add something here\n    }\n}"
    println "Created new story: " + scriptFile
}

setDefaultTarget(main)
