import sys
def main():
    command = main_menu()
    key = str()
    while command != "0": # Option to exit program
        if command == "1": # Option to change encryption key
            key = input("Please enter the encryption key: ")
        elif command == "2": # Option to encrypt a message
            if key:
                encrypt_message(key, "encrypt")
            else:
                key = input("Please enter the encryption key: ")

        elif command == "3":
        # TODO: Replace "pass" below with the
        # code to decrypt a message
            if key:
                encrypt_message(key, "decrypt")
            else:
                key = input("Please enter the encryption key: ")
        elif command == "4":
            encrypt_message("", "brute force")
        else:
            print("Invalid entry. Please select a valid option.")
            command = main_menu()
            print("Goodbye.")
        
    
def encrypt_line(line:str, key:str, mode= "encrypt") -> str:
    sign = 1
    if mode.lower() == "decrypt" or mode.lower() == "d":
        sign = -1
    elif mode.lower() == "brute force":
        return(brute_force(line))
    shift = (ord(key[0]) - 32) * sign
    result = ""
    for char in line:
        result += encrypt_letter(char, shift)
    return result

def encrypt_letter(char:str, key:int) -> str:
    index = ord(char)
    OUT_OF_BOUNDS = 127
    FIRST_VISIBLE = 32
    RANGE = OUT_OF_BOUNDS-FIRST_VISIBLE
    shifted_index = index - FIRST_VISIBLE + key
    modded_index = shifted_index % RANGE
    wrapped_index = modded_index + FIRST_VISIBLE
    result = chr(wrapped_index)
    if shifted_index >= OUT_OF_BOUNDS :
        shifted_index = shifted_index - OUT_OF_BOUNDS + FIRST_VISIBLE
    result = chr(wrapped_index)
    return result

def brute_force(line:str) -> str:
    corncob = open("corncob_lowercase.txt","r")
    dictionary = corncob.readlines()
    line = line.lower()
    words = list(line.split(" "))
    for testkey in range(93):
        testkey += 1
        result = ""
        if len(words) <= 1:
            return("Unable to brute force")
        else:
            for char in words[0]:
                result += encrypt_letter(char, -1 * testkey)
                return str(str(dictionary[0]) == "aardvark")
                #if result in dictionary:
                    #result = ""
                    #for char in words[1]:
                     #   result += encrypt_letter(char, -1 * testkey)
                    #if result in dictionary:
                     #   result = ""
                     #   for string in words:
                      #      for char in string:
                       #         result += encrypt_letter(char, -1 * testkey)
                       # print(result)
                       # return result
    return("Unable to brute force")
        
        

    

def main_menu() -> str:
    print("[1] Enter Encryption Key")
    print("[2] Encrypt a Message")
    print("[3] Decrypt a Message")
    print("[4] Brute-Force")
    print("[0] Exit Program")
    # We call strip after calling input to get rid of any
    # leading or trailing white space entered by the user
    return input("Please select an option: ").strip()

def encrypt_message(key:str, mode:str) -> None:
    result = ""
    for line in sys.stdin:
        line = line.strip()
        result += encrypt_line(line, key, mode) + "\n"
    print(result)

if __name__ == "__main__":
    main()