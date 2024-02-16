## Problem-statement
The producer-consumer problem is a classic synchronization problem in computer science that models the interaction between two processes: a producer and a consumer. Here's an explanation with diagrams:

Conceptual Overview:

Producer: Generates data (items) and places them in a shared buffer.
Consumer: Consumes data (items) from the shared buffer.
Key Challenges:

Buffer Overflow: The producer must not add data when the buffer is full.
Buffer Underflow: The consumer must not try to consume data when the buffer is empty.
Mutual Exclusion: Only one process (producer or consumer) can access the buffer at a time.