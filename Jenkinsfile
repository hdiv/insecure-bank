node{
  on_commit to: develop, {
    ws_sca()
  }

  on_merge to: master, from: develop, {
    maven.run(["clean", "install"])
    ws_sca()
  }
}
