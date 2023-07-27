from nba_api.stats.endpoints import winprobabilitypbp,teamgamelog,leaguegamefinder,videoevents,playbyplayv2,boxscoretraditionalv2
from nba_api.live.nba.endpoints import scoreboard
from nba_api.stats.static import teams,players
import pandas as pd
import time
import requests
import json


wonftandgame = 0
gamenum = 0
gamefinder = leaguegamefinder.LeagueGameFinder(season_nullable="2019-20",league_id_nullable="00",season_type_nullable="Regular Season", headers={'Accept': 'application/json, text/plain, */*',
'Accept-Encoding': 'gzip, deflate, br',
'Accept-Language': 'en-US,en;q=0.9',
'Connection': 'keep-alive',
'Host': 'stats.nba.com',
'Origin': 'https://www.nba.com',
'Referer': 'https://www.nba.com/',
'sec-ch-ua': '"Google Chrome";v="87", "\"Not;A\\Brand";v="99", "Chromium";v="87"',
'sec-ch-ua-mobile': '?1',
'Sec-Fetch-Dest': 'empty',
'Sec-Fetch-Mode': 'cors',
'Sec-Fetch-Site': 'same-site',
'User-Agent': 'Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Mobile Safari/537.36',
'x-nba-stats-origin': 'stats',
'x-nba-stats-token': 'true'})
games = gamefinder.get_data_frames()[0]
for i in range(0,300):
    if str(games["WL"][i]) == "W":
        gamenum = gamenum + 1
        box = boxscoretraditionalv2.BoxScoreTraditionalV2(games["GAME_ID"][i])
        box = box.team_stats.get_data_frame()
        winner = box[pd.to_numeric(box['PLUS_MINUS']) >= 0]
        loser = box[pd.to_numeric(box['PLUS_MINUS']) <= 0]
        winner = winner["FTA"].values[0]
        loser = loser["FTA"].values[0]
        if (winner > loser):	
            wonftandgame += 1

print(wonftandgame*100/gamenum)      
plays = {}



