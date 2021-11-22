- Joseph Attieh
- Clara Akiki
- 
# Character-Recognition
_Submitted in Dec 2018 for COE 544 - Intelligent Engineering Algorithms_

This following project was featured during the Engineering Week / Pioneer's day and was awarded a 22/20 on the project.

## Requirements
The project's objective is to build an Optical Character Recognition (OCR) system using the neural networks 
already implemented in Project1. The system should not rely at any point on the pixels, rather only on features extracted from the images.  


## Functionalities
To train the system, we created a dataset of characters written by us. 
The system works as follows:
1. Preprocessing of each character in the training set:
- Binarization (also known as thresholding): It is done by converting a gray-scale image into a binary image 
where a 0 corresponds to a white pixel and a 1 corresponds to a black pixel. 
- Minimum bounded rectangle: It is done by identifying the useful part of our image. It is done by finding the 
exact area covered by the character. 
- Skeletonizing: This is done by first applying thinning algorithms to the character in order to reduce the data 
size and normalize characters so that they are processed regardless of their width.  
- Resizing: we resized the character depending on the feature that we needed to extract. 

2. Feature Extraction: The following features were extracted from every image:
- Diagonal feature 
- Zoning feature
- Projection Histogram

3. Training the networks: we chose to use an ensemble network of 5 neural networks:
- network 1 trained on a subset of samples on the diagonal feature
- network 2 trained on a bigger subset of samples on the diagonal feature
- network 3 trained on a subset of samples on the zoning feature
- network 4 trained on a bigger subset of samples on the zoning feature
- network 5 trained on a subset of samples on the projection histogram feature

4. The decision is made by aggregating the result of every network.


The system can be showed below:
![image](https://user-images.githubusercontent.com/40753763/142798977-61f636fd-19a1-4932-94fd-c91c327748a4.png)



