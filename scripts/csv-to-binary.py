# convert a csv (Tiled) map to a binary map file

import csv

def to4bytes(num):
    return num.to_bytes(4, 'big')

def to1byte(num):
    return num.to_bytes(1, 'big')

# without .csv
file_path = 'res/levels/room-8'

csv_path = file_path + '.csv'

h = 0

buffer = []

with open(csv_path) as csv_file:
    reader = csv.reader(csv_file, delimiter = ',')
    for row in reader:
        h += 1
        w = len(row)

        for tile in row:
            buffer.append(int(tile))

out = open(file_path, 'wb')

out.write(to4bytes(w))
out.write(to4bytes(h))

for tile in buffer:
    out.write(to1byte(tile))
