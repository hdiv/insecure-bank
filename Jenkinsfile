node{
  on_commit to: develop, {
    maven.run(["clean", "compile"], profiles: ["integration-test"])
    ws_sca()
  }

  on_merge to: master, from: develop, {
    maven.run(["clean", "install", "skipTests"], profiles: ["integration-test"])
    ws_sca()
  }
}
