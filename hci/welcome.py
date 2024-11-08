import tkinter as tk

# Function to proceed to the main application
def proceed_to_app():
    # Close the welcome screen
    root.destroy()
    # Here you would typically launch the main application window
    # For demonstration, we'll just print a message
    print("Welcome to the Main Application!")

# Creating the main Welcome window
root = tk.Tk()
root.title("Welcome Screen")

# Welcome Message
welcome_message = tk.Label(root, text="Welcome to Our Application!", font=("Arial", 20, "bold"))
welcome_message.pack(pady=20)

# Instructions Text
instructions = tk.Label(root, text="Click the button below to get started.", font=("Arial", 12))
instructions.pack(pady=10)

# Proceed Button
proceed_button = tk.Button(root, text="Get Started", command=proceed_to_app, font=("Arial", 12))
proceed_button.pack(pady=20)

# Run the main loop
root.mainloop()
