import tkinter as tk
from tkinter import messagebox

# Function to handle registration
def register_participant():
    name = entry_name.get()
    age = entry_age.get()
    gender = gender_var.get()
    sport = sport_var.get()
    newsletter = newsletter_var.get()

    if name and age and gender and sport:
        messagebox.showinfo("Registration Successful", f"Participant {name} registered successfully!\nNewsletter Subscription: {'Yes' if newsletter else 'No'}")
    else:
        messagebox.showwarning("Input Error", "Please fill all fields.")

# Creating the main registration window
root = tk.Tk()
root.title("Sports Academy Registration Form")

# Name Label and Entry
label_name = tk.Label(root, text="Participant Name:")
label_name.grid(row=0, column=0, padx=10, pady=5)
entry_name = tk.Entry(root)
entry_name.grid(row=0, column=1, padx=10, pady=5)

# Age Label and Entry
label_age = tk.Label(root, text="Age:")
label_age.grid(row=1, column=0, padx=10, pady=5)
entry_age = tk.Entry(root)
entry_age.grid(row=1, column=1, padx=10, pady=5)

# Gender Label and Radio Buttons
label_gender = tk.Label(root, text="Gender:")
label_gender.grid(row=2, column=0, padx=10, pady=5)
gender_var = tk.StringVar(value="Male")
radio_male = tk.Radiobutton(root, text="Male", variable=gender_var, value="Male")
radio_female = tk.Radiobutton(root, text="Female", variable=gender_var, value="Female")
radio_other = tk.Radiobutton(root, text="Other", variable=gender_var, value="Other")
radio_male.grid(row=2, column=1, sticky='w')
radio_female.grid(row=2, column=1, sticky='center')
radio_other.grid(row=2, column=1, sticky='e')

# Sport Label and Option Menu
label_sport = tk.Label(root, text="Sport of Interest:")
label_sport.grid(row=3, column=0, padx=10, pady=5)
sport_var = tk.StringVar(value="Soccer")
sport_options = ["Soccer", "Basketball", "Tennis", "Cricket", "Swimming"]
sport_menu = tk.OptionMenu(root, sport_var, *sport_options)
sport_menu.grid(row=3, column=1, padx=10, pady=5)

# Newsletter Checkbox
newsletter_var = tk.BooleanVar()
checkbox_newsletter = tk.Checkbutton(root, text="Subscribe to Newsletter", variable=newsletter_var)
checkbox_newsletter.grid(row=4, columnspan=2, padx=10, pady=10)

# Register Button
register_button = tk.Button(root, text="Register", command=register_participant)
register_button.grid(row=5, columnspan=2, pady=10)

# Run the main loop
root.mainloop()
