---
title: "Computer Vision"
permalink: /categories/computer-vision
toc: true
toc_sticky: true
---

<br>

<div class="math-statement" markdown="1">

[목차]

- [Preliminary](#preliminary)
- [Image Classification](#image-classification)
  - [Image Processing Introduction](#image-processing-introduction)
  - [Image Classification + CV](#image-classification--cv)
  - [Image Classification + DL](#image-classification--dl)
- [CNN Architectures](#cnn-architectures)
- [Object Detection](#object-detection)
- [Sementic Segmentation](#sementic-segmentation)
  - [Instance-aware Semantic Segmentation](#instance-aware-semantic-segmentation)
- [Metric Learning](#metric-learning)
- [Video Vision](#video-vision)
  - [Video Classification + CV](#video-classification--cv)
  - [Video Classification + DL](#video-classification--dl)
  - [Visual Tracking](#visual-tracking)
- [Model Fitting](#model-fitting)
- [Camera Models](#camera-models)

</div>

### Preliminary
- Linear Algebra
- Bayes' Theorem

<br>
<hr>

### Image Classification

#### Image Processing Introduction
- Convolution
  - smoothing
    - Bilinear
    - Average
    - Gaussian
- Edge
  - Gradient Image
- Corners: Harris Corner Detection
  - Eigen Decomposition
- Blob
  - Laplace of Gaussian; LoG

<br>

#### Image Classification + CV
- SIFT (2004)

<div class="notice" markdown="1">

1. Finding Scale-Space Extrema
2. Keypoint Filtering
3. Orientation Assignment
4. Calculating Descriptor

</div>

- Spatial Pytramid Matching (2006)
- Discrimative vs. Geneartive Model

<br>

#### Image Classification + DL
- MLP
- Loss Functions
- Gradient Descent
  - SGD
  - Momentum
- CNN
- Overfitting Issue
  - Drop out
  - Weight decay
  - Early Stopping
  - Network Intialization
    - Learning from scratch
    - Xavier Initialization (2010)
    - He Initialization (2015)

<br>
<hr>

### CNN Architectures
- LeNet (1998)
- AlexNet
  - LRN; Local response Normalizatioin
- VGGNet (2014)
- ResNet (2016)
  - Degrading Problem
  - Skip Connection
  - Batch Normalization (2015)
- Beyond ResNet
  - DenseNet (2017)
    - Channel-wise concatenation
  - SENet (2017)
    - Squeeze & Excitation

<br>
<hr>

### Object Detection
- Support Vector Machine
  - Linear SVM + Separable Case
  - Linear SVM + Non-Separable Case
    - Soft margin
  - Non-Linear SVM
    - Kernel Method
  - Multi-Class SVM
- Pedestrian Detection + SVM (2005)
  - HOG <small>Histogram of Orientated Gradient</small>+ SVM

<br>

- R-CNN <small>Region-base CNN</small> (2014)
  - Object proposal
    - Selective Search
- Fast R-CNN (2015)
  - ROI pooling
- Faster R-CNN (2015)
  - Fast R-CNN + RPN <small>Region Proposal Network</small>

<br>
<hr>

### Sementic Segmentation

<div class="notice" markdown="1">

- Fully Convolutional Network (FCN Family)
  - FCN (2015)
  - DeepLab
- Convolutional Encoder-Decoders
  - U-Net
  - DeConvNet (2015)

</div>


- FCN (2015)
  - 1x1 conv
  - adding skip connection
- DeepLab (2017)
  - Atrous Convolution
  - CRF; Fully-Connected Conditional Random Field
- Pyramid Scene Parsing Network (2017)
  - Pyramid pooling module
- Context Encoding Network (2018)
  - Attension module

<br>

- DeConvNet (2015)
  - conv - deconv
  - pooling - unpooling

#### Instance-aware Semantic Segmentation

- Multi-task Network Cascades (2016)
- Multi-scale Patch Aggregation (2016)
- Mask R-CNN (2017)
  - ROI Align

<br>
<hr>

### Metric Learning
- Pairwise & Triplet Metric
- [Mahalanobis Distance]({{"2020/12/02/metric-learning-1#mahalanobis-distance" | relative_url}})
- [A first approach to distance metric learning]({{"2020/12/02/metric-learning-1#a-first-approach-to-distance-metric-learning" | relative_url}}) <small>(Pairwise)</small>
- [Large Margin Nearest Neighbor(LMNN)]({{"2020/12/02/metric-learning-1#mahalanobis-distance" | relative_url}}) <small>(Triplet)</small>
- [Metric Learning + DL]({{"2020/12/02/metric-learning-1#metric-learning--dl" | relative_url}})

<br>
<hr>

### Video Vision

#### Video Classification + CV
- Optical Flow
  - (가정) Color constancy
  - (가정) Small motion
  - Lukas-Kanade Flow
- STIP; Space-Time Interest Point (2005)
- Dense Trajectory

#### Video Classification + DL
- 3D CNN (2010)
- C3D (2015)
- Time Information Fusion (2014)
  - Sing Frame
  - Late Fusion
  - Early Fusion
  - Slow Fusion
- Two-Stream Cconvolutional Network (2014)

<br>
<hr>

#### Visual Tracking
- Probabilistic Tracking
- [Sequential Density Estimation]({{"2020/12/15/Sequential-Density-Estimation" | relative_url}})
- Kalman Filter
- Particle Filtering

<br>
<hr>

### Model Fitting
- Least Square
  - Ordinary Linear Least Square
  - Total Linear Least Square
- RANSAC <small>RANdom SAmple Consensus</small>
- Hough Transform

<br>
<hr>

### Camera Models
- 2D Objects
- 2D Transformations
  - Translation
  - Euclidean transform
  - Similarity transform
  - Affine transform
  - Projective transform
- 3D Objects
  - homogenous coordinates; $\overline{x} = [x, y, z, 1]$
- Pinhole Model
  - Intrinsic Parameters
  - Extrinsic Parameters
- Camera Clibration
  - Estimate camera prameters matrix