## 1. 概述

在本教程中，我们将介绍Spring Cloud Netflix Hystrix-容错库。
我们将使用该库并实现断路器模式，该模式描述了一种针对应用程序中不同级别的故障级联的策略。

原理类似于电子学：Hystrix监视调用相关服务失败的方法。如果出现此类故障，它将打开电路并将调用转发到fallback方法。

库将容忍故障达到阈值。除此之外，它使电路保持开放状态。
这意味着，它会将所有后续调用转发到fallback方法，以防止将来发生故障。这为相关服务从失败状态中恢复创建了一个时间缓冲区。

## 2. 