node{
  on_commit{
    maven.run(["clean", "install"])
  }

  on_merge to: master, from: develop, {
    ws_scan()
  }
}
