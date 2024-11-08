import tkinter as tk
from tkinter import messagebox

# Function to handle registration
def register_patient():
    name = entry_name.get()
    age = entry_age.get()
    gender = gender_var.get()
    symptoms = listbox_symptoms.curselection()
    selected_symptoms = [listbox_symptoms.get(i) for i in symptoms]

    if name and age and gender and selected_symptoms:
        messagebox.showinfo("Registration Successful", f"Patient {name} registered successfully!")
    else:
        messagebox.showwarning("Input Error", "Please fill all fields and select at least one symptom.")

# Creating the main window
root = tk.Tk()
root.title("Patient Registration Form")

# Name Label and Entry
label_name = tk.Label(root, text="Patient Name:")
label_name.grid(row=0, column=0, padx=10, pady=5)
entry_name = tk.Entry(root)
entry_name.grid(row=0, column=1, padx=10, pady=5)

# Age Label and Entry
label_age = tk.Label(root, text="Age:")
label_age.grid(row=1, column=0, padx=10, pady=5)
entry_age = tk.Entry(root)
entry_age.grid(row=1, column=1, padx=10, pady=5)

# Gender Label and Checkbuttons
label_gender = tk.Label(root, text="Gender:")
label_gender.grid(row=2, column=0, padx=10, pady=5)
gender_var = tk.StringVar(value="Male")
check_male = tk.Radiobutton(root, text="Male", variable=gender_var, value="Male")
check_female = tk.Radiobutton(root, text="Female", variable=gender_var, value="Female")
check_other = tk.Radiobutton(root, text="Other", variable=gender_var, value="Other")
check_male.grid(row=2, column=1, sticky='w')
check_female.grid(row=2, column=1, sticky='e')
check_other.grid(row=2, column=1)

# Symptoms Label and Listbox
label_symptoms = tk.Label(root, text="Symptoms:")
label_symptoms.grid(row=3, column=0, padx=10, pady=5)
listbox_symptoms = tk.Listbox(root, selectmode=tk.MULTIPLE)
symptoms = ["Fever", "Cough", "Headache", "Nausea", "Fatigue"]
for symptom in symptoms:
    listbox_symptoms.insert(tk.END, symptom)
listbox_symptoms.grid(row=3, column=1, padx=10, pady=5)

# Register Button
register_button = tk.Button(root, text="Register", command=register_patient)
register_button.grid(row=4, columnspan=2, pady=10)

# Run the main loop
root.mainloop()
