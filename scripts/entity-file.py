# convert an "entity position" code to bytes

def to1byte(num):
    return num.to_bytes(1, 'big')

# script: <entity> <x tile> <y tile> <entity-specific-arguments>
# entities: player, melee, king, text

brazier_welcome = [
    "player 2 17",
    "king 6 3 0",
    "text 3 13 0",
    "text 3 14 1",
    "text 11 12 2",
    "text 11 10 3",
    "text 6 6 4",
]

code = brazier_welcome

out = open('res/levels/brazier-welcome.entities', 'wb')

for row in code:
    row = row.replace('player', '0')
    row = row.replace('melee', '1')
    row = row.replace('king', '10')
    row = row.replace('text', '11')

    numbers = row.split(' ')
    for n in numbers:
        out.write(to1byte(int(n)))
