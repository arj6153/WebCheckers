<!DOCTYPE html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
  <meta http-equiv="refresh" content="5">
  <title>Web Checkers | ${title}</title>
  <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>

<body>
<div class="page">
  <h1>Web Checkers | ${title}</h1>
  <div class ="navigation">
  <#include "nav-bar.ftl" />
  </div>

  <div class="body">

    <p>Welcome to checkers!</p>
    <#include "message.ftl" />
    <br><br/>
    <#include "view-players.ftl"/>
    <!-- TODO: future content on the Home:
                   to start games,
                   spectating active games,
                   or replay archived games
           -->
  </div>

</div>
</body>

</html>
