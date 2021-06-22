libraries {
	git {
	   github
	   on_commit
	   on_merge
	}
	maven {
	   mavenID = "maven"
	}
	whitesource {
	   Product = "WhiteSource_Test" 
    	   Project = "Test_jenkins"
           Package_Manager = "maven"
	}
}
