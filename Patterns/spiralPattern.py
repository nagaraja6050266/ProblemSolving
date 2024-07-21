'''
Enter the n value: 12
* * * * * * * * * * * *
* . . . . . . . . . . *
* . * * * * * * * * . *
* . * . . . . . . * . *
* . * . * * * * . * . *
* . * . * . . * . * . *
* . * . * . . * . * . *
* . * . * * * * . * . *
* . * . . . . . . * . *
* . * * * * * * * * . *
* . . . . . . . . . . *
* * * * * * * * * * * *

Enter the n value: 8
* * * * * * * *
* . . . . . . *
* . * * * * . *
* . * . . * . *
* . * . . * . *
* . * * * * . *
* . . . . . . *
* * * * * * * *

'''

n=int(input("Enter the n value: "))
a='*'
b='.'
printable=a

arr=[[b for _ in range(n//2)] for _ in range(n//2)]

for i in range(n//2):
    for j in range(i,n//2):
        arr[i][j]=printable
    if printable==a:
        printable=b
    else:
        printable=a

for i in range(n//2):
    for j in range(i,n//2):
        arr[j][i]=printable
    if printable==a:
        printable=b
    else:
        printable=a

for i in range(n//2):
    arr[i]=arr[i]+arr[i][::-1]

arr=arr+arr[::-1]



for i in range(n):
    for j in range(n):
        print(arr[i][j],end=" ")
    print()


