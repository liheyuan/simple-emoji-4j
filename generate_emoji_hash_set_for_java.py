#!/usr/bin/python3
import urllib.request

EMOJI_VERSION = "12.0"
URL = "http://unicode.org/Public/emoji/{}/emoji-data.txt".format(EMOJI_VERSION)

def not_normal_num(num):
    return num > 256

def not_normal(str):
    x = int(str, 16)
    return x > 256

if __name__ == "__main__":
    emoji_set = set()
    for line in urllib.request.urlopen(URL).readlines():
        line = line.decode()
        if line.startswith("#"):
            continue
        tmp = line.split(";")
        str = tmp[0].strip()
        if len(str) == 0:
            continue
        if ".." in str:
            (begin, end) = str.split("..")
            try:
                for x in range(int(begin, 16), int(end, 16) + 1):
                    if not_normal_num(x): emoji_set.add("{0:x}".format(x))
            except:
                pass
        else:
            if not_normal(str): emoji_set.add(str.lower())
        #print(line)

    # begin
    print('// Generated from {}'.format(URL))
    print('Set<String> EMOJI_HASH_SET = new HashSet<>(Arrays.asList(')
    for (idx, val) in enumerate(sorted(emoji_set)):
        print('"\\u{}"'.format(val), end='')
        if idx != len(emoji_set) - 1:
            print(",", end='')
        if (idx % 10) == 9:
            print("") 
    print('));')
        
