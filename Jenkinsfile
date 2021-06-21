node{
  on_commit to: master, {
    maven.run(["clean", "install"])
  }

  on_merge to: master, from: develop, {
    ws_scan()
  }
}
