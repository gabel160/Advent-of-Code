import os
import regex as re
here = os.path.dirname(os.path.abspath(__file__))
filename = os.path.join(here, 'input.txt')
lines = 0
result = 0
with open(filename) as f:
    lines = f.readlines()
    
#PART1
for line in lines:
    x = re.sub('\D', '', line)
    x = x[0] + x[-1]
    result += int(x)
print("Part 1", result)

#Part 2
result2 = 0
convert = {'one': "1", 'two': "2", 'three': "3", 'four': "4", 'five': "5", 'six': "6", 'seven': "7", 'eight': "8", 'nine': "9"}
for line in lines:
    clean = re.findall('([0-9]|zero|one|two|three|four|five|six|seven|eight|nine)',  line, overlapped=True)
    x = ""
    if clean[0] in convert:
        x = convert[clean[0]]
    else:
        x = str(clean[0])
    if clean[-1] in convert:
        x = x + convert[clean[-1]]
    else:
        x = x + str(clean[-1])
    result2 += int(x)
print("part2", result2)