<div class="activePlayers">
    <#if currentUser??>
        List
    <#else>
        Server Population: ${lobbyCount}
    </#if>
</div>