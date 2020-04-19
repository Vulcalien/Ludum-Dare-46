# convert an "entity position" code to bytes

def to1byte(num):
    return num.to_bytes(1, 'big')

# script: <entity> <x tile> <y tile>
# entities: player, melee, king

code = [
    "player 10 15",
    "king 7 8"
]

out = open('res/levels/brazier-map.entities', 'wb')

for row in code:
    row = row.replace('player', '0')
    row = row.replace('melee', '1')
    row = row.replace('king', '10')

    numbers = row.split(' ')
    for n in numbers:
        out.write(to1byte(int(n)))
