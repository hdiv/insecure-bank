jte {
  pipeline_template = "java"
}

libraries {
  maven {
    maven {
      version = "3.8.1"
      //settings = "settings.xml"
      //modules = "test1,test2,core"
    }
  
    aws {
      defaultregion = "us-east-1"
      codeartifact_action = "get-authorization-token"
      codeartifact_domain = "test"
      codeartifact_domain_owner = "111111115571"
      codeartifact_query = "authorizationToken"
      codeartifact_output = "text"
  	}
  }
  
  checkmarx {
    ProjectName = "CxServer\\SP\\Solarwinds\\MSP_N-Central\\Test_project"
    Language = "Java"
  }
  
  whitesource {
    Product = "WhiteSource_Test"
    Project = "Test_jenkins"
  }
}
