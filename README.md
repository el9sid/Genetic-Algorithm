# Project: Schedule-Generator

## Problem Statement:
###### Creating a schedule without any conflict is a tedious job and could take up a lot of time if done manually. At the start of every semester, every school has to create a schedule of lectures for that particular session.

## Solution
###### Our solution is to use the genetic algorithm to ease this task of schedule creation. Our Schedule-Generator takes in data in the form of:

1. Departments
2. Professor 
3. Course
4. Lecture Time
5. Class Room

###### and generates a schedule that is conflict free.

## Project Extensions
###### Scheduling is involved in all the activities that have to be executed as per the plan, like lecture scheduling, flight scheduling etc. This project of ours can be utilized in any situation to generate a minimum conflict schedule with minor changes.  

## Fitness Function
###### A solution is deemed fit if it has the least number of conflicts. Our fitness function is inversely proportional to the number of conflicts.

## Running the project
###### Steps to run the project:
1. Download the GeneticAlgorithm.jar file
2. Open cmd prompt and navigate to the file location
3. Run "java -cp Genetic-Algorithm.jar neu.algos.proj.ga.GenerateSchedule" command
4. It will generate a schedule in the console

## Report
###### Our project report explains the problem in a detailed way. It is kept under the Reports folder.

## Created by
1. Siddhant Singhal
2. Krapali Rai