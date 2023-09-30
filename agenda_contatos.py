import xml.etree.ElementTree as ET

def criar_contato(nome, telefone, email):
    try:
        tree = ET.parse('contatos.xml')
        root = tree.getroot()
    except FileNotFoundError:
        root = ET.Element('contatos')
        tree = ET.ElementTree(root)

    contato = ET.Element('contato')

    nome_element = ET.Element('nome')
    nome_element.text = nome
    contato.append(nome_element)

    telefone_element = ET.Element('telefone')
    telefone_element.text = telefone
    contato.append(telefone_element)

    email_element = ET.Element('email')
    email_element.text = email
    contato.append(email_element)

    root.append(contato)

    tree.write('contatos.xml')

def inserir_contato():
    print("Inserir novo contato")
    nome = input("Nome: ")
    telefone = input("Telefone: ")
    email = input("Email: ")

    criar_contato(nome, telefone, email)

if __name__ == "__main__":
    inserir_contato()