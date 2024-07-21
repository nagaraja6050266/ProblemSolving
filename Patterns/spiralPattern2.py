'''
Enter the n value: 13
* * * * * * * * * * * * *
. . . . . . . . . . . . *
* * * * * * * * * * * . *
* . . . . . . . . . * . *
* . * * * * * * * . * . *
* . * . . . . . * . * . *
* . * . * * * . * . * . *
* . * . * . . . * . * . *
* . * . * * * * * . * . *
* . * . . . . . . . * . *
* . * * * * * * * * * . *
* . . . . . . . . . . . *
* * * * * * * * * * * * *

Enter the n value: 9
* * * * * * * * *
. . . . . . . . *
* * * * * * * . *
* . . . . . * . *
* . * * * . * . *
* . * . . . * . *
* . * * * * * . *
* . . . . . . . *
* * * * * * * * *

'''

a='*'
b='.'
n=int(input("Enter the n value: "))
arr=[[b for _ in range(n)] for _ in range(n)]

i,j=0,0
printCount=n

def printArray(arr):
    for i in range(n):
        for j in range(n):
            print(arr[i][j],end=" ")
        print()


#MoveRight
for c in range(printCount-1):
    arr[i][j]=a
    j+=1


while (printCount>0):
    #MoveDown
    for c in range(printCount-1):
        arr[i][j]=a
        i+=1


    #MoveLeft
    for c in range(printCount-1):
        #print([i,j])
        arr[i][j]=a
        j-=1


    printCount-=2



    #MoveUp
    for c in range(printCount-1):
        arr[i][j]=a
        i-=1


    #MoveRight
    for c in range(printCount-1):
        arr[i][j]=a
        j+=1
    printCount-=2


arr[i][j]=a

printArray(arr)