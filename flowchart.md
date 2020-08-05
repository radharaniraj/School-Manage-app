graph TD
	A(Start) --> B{Login}
	B --> |failed|B
	B --> |success|C[Home Page]
	C --> D[View Home Works]
	C --> E[View Attendance]
	C --> F[View Complaints]
	C --> G[View Holidays]
	C --> H[View Notice]
	C --> I[Log out]
	I --> J(Exit)