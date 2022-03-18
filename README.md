# token-bucket-algo

Token Bucket Algorithm,is used to average inflow rate of the traffic/network. Different devices send data(packets) to the network which can either be transmitted successfully or rejected depending on the availability of the tokens in the intermediate device. So the packets that are received in a burst are outputted in a specific transmitting order and the number of packets successfully transmitted and the number of packets lost are calculated.

The control flow of the program:
•	The program execution starts with the MainClass which creates an instance of CreateGUI class using constructor, thus creating the input screen.
•	When the user enters the number of external devices and the number of tokens assigned to intermediate device and presses the submit button, the input screen is automatically closed and the input values are used to create instances of the ExternalDevice class and initialize the number of initial tokens in the IntermediateClass.
•	Based on the static attribute ‘memory’ the number of packets that are passed to the IntermediateClass are decided and are added to an ArrayList while the rest of the packets are dropped immediately.
•	Depending on the ArrayList the threads are created and started in succession using for loop.
•	The next for loop that operates join() on each thread has been implemented to ensure that these threads run before the execution of the thread that follows this snippet which has been created using abstract runnable class.
•	The run() is executed in the IntermediateDevice class and executes the transmit() and setTokens() methods that hold the logic for transmitting/rejecting and updating the number of tokens respectively.
•	Inside the run block the time that passes has also been calculated so as to add tokens to the token bucket after each second using the setTokens() method.
•	When all the threads have been executed an instance of the OutputScreen is created which renders the output screen.
•	The constructor of the OutputScreen takes an ArrayList containing the strings that have to be printed on the output screen using the textArea object of the JFrame.

Note: GUI has been implemented using Java Swing.


Other important information:
->The project was developed using the eclipse IDE Version: 2021-06 (4.20.0) and the src folder had JRE JavaSE 16 as its runtime environment. The project has been tested both in the IDE with the above specified JRE and in Windows 10 Pro using the cmd through javac and java commands
->•	The GUI has been implemented using Java Swing and addition of extra libraries and inheriting classes like JFrame. These additional libraries were also available by default so no other installation or software has been used as such.

