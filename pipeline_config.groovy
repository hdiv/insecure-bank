jte {
  pipeline_template = "test"
}

libraries {
  maven {
    maven {
      version = "3.8.1"
      //artifact_version = "1.0.1"
      //settings = "settings.xml"
      //modules = "test1,test2,core"b
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
    CxCred  = "Cx-Access"
  }
  
  whitesource {
    Product = "WhiteSource_Test"
    Project = "Test_jenkins"
    User_Key = "user_key"
  }
  
  trivy {
    Image_Name = "vuln-check:insecure-bank"
    Break_Build = 0
    Report_Format = "table"
  }
}
