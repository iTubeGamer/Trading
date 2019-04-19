f = open("input.txt", "r")
command = ""
for line in f:
    command = command + line[:6] + ","
print(command)
