import os
import re
here = os.path.dirname(os.path.abspath(__file__))
filename = os.path.join(here, 'input.txt')
lines = 0
result = 0
with open(filename) as f:
    lines = f.readlines()

minimum = {"red": 0, "blue": 0, "green" : 0}
gameid = 1
total = 0
fail = False
for line in lines:
    minimum = {"red": 0, "blue": 0, "green" : 0}
    nogame = line.split(":")

    eachgame = nogame[1].split(";")
    fail = False
    for game in eachgame:
        
        split = game.split(",")
        for word in split:
            if word.find("red") != -1:
                num = re.findall(r'\d+', word) 
                if int(num[0]) > minimum["red"]:
                    minimum["red"] = int(num[0])
            if word.find("blue") != -1:
                num = re.findall(r'\d+', word)
                if int(num[0]) > minimum["blue"]:
                    minimum["blue"] = int(num[0])
            if word.find("green") != -1:
                num = re.findall(r'\d+', word)
                if int(num[0]) > minimum["green"]:
                    minimum["green"] = int(num[0])
    math = minimum["red"] * minimum["blue"] * minimum["green"]
    print(gameid, minimum, math)

    total += math
    gameid +=1
    # if not fail:
    #     total += gameid
    # gameid += 1

print(total)