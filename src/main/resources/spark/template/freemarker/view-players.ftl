<div class="activePlayers">
    <#if currentUser??>
        <form action="/game" method="GET">
            <input type="submit" name="ai_player" value=${"ai_player"}>
        <#if playerList??>
            <h2>Online Player</h2>
            <#list playerList?keys as key>
                <#if playerList[key].name != currentUser.name>
                    <form action="/game" method="GET">
                        <input type="submit" name="player" value=${playerList[key].name}>
                </#if>
            </#list>
        </#if>
    <#else>
        Server Population: ${lobbyCount}
    </#if>
</div>