libraries {
	sdp {
	  images {
	    registry = "https://docker.pkg.github.com"
	    repository = "boozallen/sdp-images"
	    cred = "public-github"
	    docker_args = "-u 0:0"
	  }
        }
	github {
	   source_type = "github"
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
