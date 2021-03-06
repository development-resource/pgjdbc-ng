
PGConnection connection = DriverManager.getConnection(url).unwrap(PGConnection.class); // <1>
connection.addListener(new PGNotificationListener() { // <2>

  void notification(int processId, String channelName, String payload) {
    System.out.println("Received Notification: " + processId + ", " + channelName + ", " + payload); // <3>
  }

  void closed() { // <4>
    // initiate reconnection & restart listening
  }

});


Statement stmt = connection.createStatement();
stmt.executeUpdate("LISTEN msgs"); // <5>

stmt.executeUpdate("NOTIFY msgs"); // <6>
