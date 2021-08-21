#include <iostream>
#include<windows.h>
#include <iomanip>
#define MAX 100
using namespace std;

HANDLE console = GetStdHandle(STD_OUTPUT_HANDLE);
COORD CursorPosition;
void gotoxy(int x, int y);

int main()
{
 int arr[MAX];
 int n=0,temp,i,j;
 
 cout << "\t \t \tSorting Arrays\n";
 cout<<"Number to Sort: ";
 cin>>n;
 cout<<"\nEnter Elements: "<<endl;
 	for(i=0;i<n;i++)
 	{
 	gotoxy(6 * i ,5);
 	cin>>arr[i];
 	}
 	
 	//this finds the maximum and minimum of the value
 	for(i=0;i<n;i++)
 	{
 	for(j=0;j<n;j++)
 		{
 			if(arr[i]<arr[j])
 		{
 				temp=arr[i];
 				arr[i]=arr[j];
 				arr[j]=temp;
 		}
 	}
 }
 //sort arrays from lowest to highest
 cout<<"\nLowest->Highest"<<endl;
 	for(i=0;i<n;i++)
 	{
 		cout<<"\t"<<arr[i];
 	}
 	for(i=0;i<n;i++)
 	{
 		for(j=0;j<n;j++)
 		{
 			if(arr[i]>arr[j])
 			{
 				temp=arr[i];
 				arr[i]=arr[j];
		 		arr[j]=temp;
 			}
 		}
 	}
 //sort arrays from highest to lowest
 cout<<"\nHighest->Lowest"<<endl;
 	for(i=0;i<n;i++)
 	{
 		cout<<"\t"<<arr[i];
 	}
 
 //The maximum and minimum of the value
 cout<<endl<<endl;
 cout<<"\tHighest: "<<arr [0];
 cout<<" \tLowest: "<<arr [n-1];
 return 0;
}

void gotoxy(int x, int y) 
{ 
	CursorPosition.X = x; // Locates column
	CursorPosition.Y = y; // Locates Row
	SetConsoleCursorPosition(console,CursorPosition); // Sets position for next thing to be printed 
}
