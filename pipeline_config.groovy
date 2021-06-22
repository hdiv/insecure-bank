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
	   ws_product = "WhiteSource_Test" 
    	   ws_project = "Test_jenkins"
           ws_config  = "ws_mvn.config"
	}
}
