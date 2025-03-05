import sys

def add(a, b):
    return a + b

def sub(a, b):
    return a - b

su1 = int(sys.argv[1])
su2 = int(sys.argv[2])

print(add(su1, su2))
print(sub(su1, su2))