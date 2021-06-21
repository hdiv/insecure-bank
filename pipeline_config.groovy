libraries {
	git {
	   github {
	      source_type = "github"
	   }
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
