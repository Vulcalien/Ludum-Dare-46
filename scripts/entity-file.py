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

room_0 = [
    "player 9 17",
    "text 9 10 5",
    "text 9 11 6",
    "text 9 12 7",
    "text 14 1 8",
    "text 14 2 9",
    "text 4 1 10"
]

room_1 = [
    "player 2 17",
    "melee 3 3",
    "melee 16 3",
    "text 3 16 11"
]

room_2 = [
    "player 5 9",
    "melee 2 4"
]

room_3 = [
    "player 2 13",
    "melee 9 5",
    "melee 7 2"
]

room_4 = [
    "player 9 1",
    "melee 11 3",
    "melee 17 17"
]

room_5 = [
    "player 7 6",
    "melee 1 7",
    "melee 13 7"
]

room_6 = [
    "player 3 2"
]

room_7 = [
    "player 9 5",
    "melee 2 2",
    "melee 2 8"
]

room_8 = [
    "player 10 19",
    "melee 2 2",
    "melee 6 2",
    "melee 10 2",
    "melee 14 2",
    "melee 18 2"
]

brazier_end = [
    "player 18 6",
    "king 6 3 1",
    "text 16 4 12"
]

code = brazier_end

out = open('res/levels/brazier-end.entities', 'wb')

for row in code:
    row = row.replace('player', '0')
    row = row.replace('melee', '1')
    row = row.replace('king', '10')
    row = row.replace('text', '11')

    numbers = row.split(' ')
    for n in numbers:
        out.write(to1byte(int(n)))
