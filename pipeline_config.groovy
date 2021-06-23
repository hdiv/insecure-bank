jte {
  pipeline_template = "java"
}

libraries {
  maven {
    mavenId = "maven"
  }
  
  whitesource {
    Product 	= "WhiteSource_Test"
    Project 	= "Test_jenkins"
  }
}
