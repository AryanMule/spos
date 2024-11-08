import tkinter as tk
from tkinter import messagebox

# Function to handle sign-up
def sign_up():
    username = entry_username.get()
    password = entry_password.get()
    email = entry_email.get()

    if username and password and email:
        messagebox.showinfo("Sign Up Success", f"Welcome, {username}!")
    else:
        messagebox.showwarning("Input Error", "All fields are required.")

# Creating the main window
root = tk.Tk()
root.title("Sign Up")

# Username Label and Entry
label_username = tk.Label(root, text="Username:")
label_username.grid(row=0, column=0, padx=10, pady=5)
entry_username = tk.Entry(root)
entry_username.grid(row=0, column=1, padx=10, pady=5)

# Password Label and Entry
label_password = tk.Label(root, text="Password:")
label_password.grid(row=1, column=0, padx=10, pady=5)
entry_password = tk.Entry(root, show="*")
entry_password.grid(row=1, column=1, padx=10, pady=5)

# Email Label and Entry
label_email = tk.Label(root, text="Email:")
label_email.grid(row=2, column=0, padx=10, pady=5)
entry_email = tk.Entry(root)
entry_email.grid(row=2, column=1, padx=10, pady=5)

# Sign Up Button
sign_up_button = tk.Button(root, text="Sign Up", command=sign_up)
sign_up_button.grid(row=3, columnspan=2, pady=10)

# Run the main loop
root.mainloop()
