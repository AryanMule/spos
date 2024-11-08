import tkinter as tk
from tkinter import messagebox

# Function to close the Help window
def close_help():
    root.destroy()

# Creating the main Help window
root = tk.Tk()
root.title("Help Screen")

# Help Title
label_title = tk.Label(root, text="Help and Support", font=("Arial", 16, "bold"))
label_title.pack(pady=10)

# Instructions Text
instructions = (
    "Welcome to the App Help Screen!\n\n"
    "Here are some guidelines to help you use the application:\n"
    "- To create a new account, click on 'Sign Up'.\n"
    "- To log in, enter your credentials in the login screen.\n"
    "- For fund transfers, navigate to the 'Transfer' section and fill in the required details.\n"
    "- If you encounter any issues, visit the 'Support' section for assistance.\n"
    "- For feedback, use the 'Feedback' form in the menu.\n"
)
label_instructions = tk.Label(root, text=instructions, justify=tk.LEFT)
label_instructions.pack(padx=20, pady=10)

# Close Button
close_button = tk.Button(root, text="Close", command=close_help)
close_button.pack(pady=10)

# Run the main loop
root.mainloop()
