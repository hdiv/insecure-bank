node{
  on_commit to: develop, {
    maven.run(["clean", "compile"])
    ws_scan()
  }

  on_merge to: master, from: develop, {
    maven.run(["clean", "install"])
    ws_scan()
  }
}
